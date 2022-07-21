package com.lhh.lnstagram.https;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;

public class SSLSocketClient {
 
    //获取这个SSLSocketFactory
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    //获取TrustManager
    private static TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }
 
                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }
 
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        return trustAllCerts;
    }

    //获取HostnameVerifier
    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }


    /**
     * SSL Pinning 获取证书
     * @return certificata
     */
    public static CertificatePinner getCertificate(Context ctx, String strCertificatesPath) {

        Certificate ca = null;

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = ctx.getAssets().open(strCertificatesPath);;

            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }

        String certPin = "";
        if (ca != null) {
            certPin = CertificatePinner.pin(ca);
        }
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("", certPin)
                .build();

        return certificatePinner;
    }


}

