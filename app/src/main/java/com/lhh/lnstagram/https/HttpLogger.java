package com.lhh.lnstagram.https;



/**
 * @author：tqzhang on 18/9/8 21:31
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
//        if (message.startsWith("--> POST")) {
//            mMessage.setLength(0);
//        }
//        // 格式化数据
//        if ((message.startsWith("{") && message.endsWith("}"))
//                || (message.startsWith("[") && message.endsWith("]"))) {
//            message = formatJson(message);
//        }
//
//        mMessage.append(message.concat("\n"));
//        if (message.startsWith("<-- END HTTP")) {
//            LogUtil.d("Http", mMessage.toString());
//        }
    }

    /**
     * 格式化json字符串
     *
     * @param jsonStr 需要格式化的json串
     * @return 格式化后的json串
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(jsonStr).append('\n').append('\n');
        char last = '\0';
        char current = '\0';
        int indent = 0;
        int line = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                //遇到{ [换行，且下一行缩进
                case '{':
                case '[':
                    line++;
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                //遇到} ]换行，当前行缩进
                case '}':
                case ']':
                    line++;
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                //遇到,换行
                case ',':
                    line++;
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        // 一模一样返回
        if (line > 100 || true) {
            return jsonStr;
        }
        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}