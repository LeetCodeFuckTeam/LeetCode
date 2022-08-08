package leetcode.editor.cn;


import java.io.IOException;
import java.net.MalformedURLException;

public class Test13 {
//    public static void main(String args[]) {
//
//            String sUrl = "https://gitee.com/login";//网址
//            //webclient设置
//            WebClient webClient = new WebClient(BrowserVersion.CHROME); //创建一个webclient
//            webClient.getOptions().setJavaScriptEnabled(true); // 启动JS
//            webClient.getOptions().setUseInsecureSSL(true);//忽略ssl认证
//            webClient.getOptions().setCssEnabled(false);//禁用Css，可避免自动二次请求CSS进行渲染
//            webClient.getOptions().setThrowExceptionOnScriptError(false);//运行错误时，不抛出异常
//            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//            webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
//            //登录
//            try {
//                HtmlPage page = (HtmlPage) webClient.getPage(sUrl);
//                HtmlForm form = page.getForms().get(0);//page.getFormByName("");
//                HtmlTextInput txtUName = (HtmlTextInput) form.getInputByName("user[login]"); //用户名text框
//                txtUName.setValueAttribute("18391713434");
//                HtmlPasswordInput txtPwd = (HtmlPasswordInput) form.getInputByName("user[password]");//密码框
//                txtPwd.setValueAttribute("lh20010326");
//                //submit没有name，只有class和value属性，通过value属性定位元素
//                HtmlSubmitInput submit = (HtmlSubmitInput) form.getInputByValue("登 录");
//                page = (HtmlPage) submit.click();//登录进入
//                webClient.waitForBackgroundJavaScript(10000);//等待1秒
//
//                System.out.println(page.asText());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//    }
//    public static void loginGitee() {
//        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
//        WebClient webclient = new WebClient();
//
//        // 这里是配置一下不加载css和javaScript,配置起来很简单，是不是
//        webclient.getOptions().setCssEnabled(false);
//        webclient.getOptions().setJavaScriptEnabled(false);
//
//        // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
//        HtmlPage htmlpage = null;
//        try {
//            htmlpage = (HtmlPage) webclient
//                    .getPage("http://news.baidu.com/");
//        } catch (FailingHttpStatusCodeException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (MalformedURLException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//
//        // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
//        final HtmlForm form = htmlpage.getFormByName("f");
//        // 同样道理，获取”百度一下“这个按钮
//        final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByValue("百度一下");
//        // 得到搜索框
//        final HtmlTextInput textField = (HtmlTextInput) form.getInputByName("q1");
//        // 最近周星驰比较火呀，我这里设置一下在搜索框内填入”周星驰“
//        textField.setValueAttribute("苍井空");
//        // 输入好了，我们点一下这个按钮
//        HtmlPage nextPage = null;
//        try {
//            nextPage = (HtmlPage) button.click();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // 我把结果转成String
//        String result = nextPage.asXml();
//        System.out.println(result);
//    }
}
