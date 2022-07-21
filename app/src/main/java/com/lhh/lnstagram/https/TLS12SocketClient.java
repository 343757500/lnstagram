package com.lhh.lnstagram.https;

import android.os.Build;
import android.util.Log;

import com.lhh.lnstagram.base.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

public class TLS12SocketClient {

    public static void CompatTLS12OkHttpClient(OkHttpClient.Builder mOkHttpClientBuilder) {

//        if (BaseApplication.isDebug()) {
//            // 单向证书
//            mOkHttpClientBuilder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
//        } else {
//            // V2采用双向证书
//            mOkHttpClientBuilder.sslSocketFactory(TLS12SocketClient.getSSLSocketFactory());
//        }

        //mOkHttpClientBuilder.sslSocketFactory(TLS12SocketClient.getSSLSocketFactory());
        mOkHttpClientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());

        // Android4.x 强制打开TLS1.2
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                mOkHttpClientBuilder.sslSocketFactory(new TLS12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                mOkHttpClientBuilder.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        String[] strCertificatesPath = new String[]{"2733904__im.sasai.mobi.pem", "4367363__im.sasai.mobi.pem", "6215162__im.sasai.mobi.pem"};
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (String certificatePath : strCertificatesPath) {
                InputStream certificate = BaseApplication.getInstance().getAssets().open(certificatePath);
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(
                    null,
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
            );


            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

