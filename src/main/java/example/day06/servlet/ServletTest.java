package example.day06.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/day06/test")
public class ServletTest extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ServletController.init");
        System.out.println("해당 클래스 서블릿 객체 생성");
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.service");
        System.out.println("서블릿 객체 서비스 실행");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("ServletController.destroy");
        System.out.println("서블릿 객체 초기화");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        System.out.println("HTTP의 get 메소드방식으로 요청");
        //super.doGet(req, resp); //super.메소드 ()
        //요청
        System.out.println("request Data:"+req.getParameter("value"));
        //응답
        resp.getWriter().print(Integer.parseInt(req.getParameter("value"))+2);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        System.out.println("post요청");
        //super.doPost(req, resp);
        //요청
        System.out.println("request Data:"+req.getParameter("value"));
        //응답
        resp.getWriter().print(Integer.parseInt(req.getParameter("value"))*2);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        System.out.println("put 요청");
        //super.doPut(req, resp);
        //요청
        System.out.println("request Data:"+req.getParameter("value"));
        //응답
        resp.getWriter().print(Integer.parseInt(req.getParameter("value"))/2);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        System.out.println("delete 요청");
        //super.doDelete(req, resp);
        //요청
        System.out.println("request Data:"+req.getParameter("value"));
        //응답
        resp.getWriter().print(Integer.parseInt(req.getParameter("value"))%2);

    }


}
