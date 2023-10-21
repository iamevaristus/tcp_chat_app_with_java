//package chatX.app;
//
//// Import required java libraries
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//// Extend HttpServlet class
//public class Main extends HttpServlet {
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Set response content type
//        response.setContentType("text/html");
//
//        PrintWriter out = response.getWriter();
//        String title = "Using GET Method to Read Form Data";
//        String docType =
//                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//        out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor = \"#f0f0f0\">\n" +
//                "<h1 align = \"center\">" + title + "</h1>\n" +
//                "<ul>\n" +
//                "  <li><b>First Name</b>: "
//                + request.getParameter("first_name") + "\n" +
//                "  <li><b>Last Name</b>: "
//                + request.getParameter("last_name") + "\n" +
//                "</ul>\n" +
//                "</body>" +
//                "</html>"
//        );
//    }
//
//    GET Method Example Using Form
//
//    Here is a simple example which passes two values using HTML FORM and submit button. We are going to use same Servlet HelloForm to handle this input.
//
//<html>
//   <body>
//      <form action = "HelloForm" method = "GET">
//    First Name: <input type = "text" name = "first_name">
//         <br />
//    Last Name: <input type = "text" name = "last_name" />
//         <input type = "submit" value = "Submit" />
//      </form>
//   </body>
//</html>
//
//            // Import required java libraries
//            import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//    // Extend HttpServlet class
//    public class HelloForm extends HttpServlet {
//
//        // Method to handle GET method request.
//        public void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            // Set response content type
//            response.setContentType("text/html");
//
//            PrintWriter out = response.getWriter();
//            String title = "Using GET Method to Read Form Data";
//            String docType =
//                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
//                            "transitional//en\">\n";
//
//            out.println(docType +
//                            "<html>\n" +
//                            "<head><title>" + title + "</title></head>\n" +
//                            "<body bgcolor = \"#f0f0f0\">\n" +
//                            "<h1 align = \"center\">" + title + "</h1>\n" +
//                            "<ul>\n" +
//                            "  <li><b>First Name</b>: "
//                            + request.getParameter("first_name") + "\n" +
//                            "  <li><b>Last Name</b>: "
//                            + request.getParameter("last_name") + "\n" +
//                            "</ul>\n" +
//                            "</body>"
//                    "</html>"
//            );
//        }
//
//        // Method to handle POST method request.
//        public void doPost(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            doGet(request, response);
//        }
//    }
//    Now compile and deploy the above Servlet and test it using Hello.htm with the POST method as follows −
//
//<html>
//   <body>
//      <form action = "HelloForm" method = "POST">
//    First Name: <input type = "text" name = "first_name">
//         <br />
//    Last Name: <input type = "text" name = "last_name" />
//         <input type = "submit" value = "Submit" />
//      </form>
//   </body>
//</html>
//    Here is the actual output of the above form, Try to enter First and Last Name and then click submit button to see the result on your local machine where tomcat is running.
//
//    First Name:  Last Name:
//    Based on the input provided, it would generate similar result as mentioned in the above examples.
//
//    Passing Checkbox Data to Servlet Program
//
//    Checkboxes are used when more than one option is required to be selected.
//
//    Here is example HTML code, CheckBox.htm, for a form with two checkboxes
//
//            <html>
//   <body>
//      <form action = "CheckBox" method = "POST" target = "_blank">
//         <input type = "checkbox" name = "maths" checked = "checked" /> Maths
//            <input type = "checkbox" name = "physics"  /> Physics
//            <input type = "checkbox" name = "chemistry" checked = "checked" />
//    Chemistry
//            <input type = "submit" value = "Select Subject" />
//      </form>
//   </body>
//</html>
//    The result of this code is the following form
//
//    Maths  Physics  Chemistry
//    Given below is the CheckBox.java servlet program to handle input given by web browser for checkbox button.
//
//// Import required java libraries
//            import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//    // Extend HttpServlet class
//    public class CheckBox extends HttpServlet {
//
//        // Method to handle GET method request.
//        public void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            // Set response content type
//            response.setContentType("text/html");
//
//            PrintWriter out = response.getWriter();
//            String title = "Reading Checkbox Data";
//            String docType =
//                    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//            out.println(docType +
//                            "<html>\n" +
//                            "<head><title>" + title + "</title></head>\n" +
//                            "<body bgcolor = \"#f0f0f0\">\n" +
//                            "<h1 align = \"center\">" + title + "</h1>\n" +
//                            "<ul>\n" +
//                            "  <li><b>Maths Flag : </b>: "
//                            + request.getParameter("maths") + "\n" +
//                            "  <li><b>Physics Flag: </b>: "
//                            + request.getParameter("physics") + "\n" +
//                            "  <li><b>Chemistry Flag: </b>: "
//                            + request.getParameter("chemistry") + "\n" +
//                            "</ul>\n" +
//                            "</body>"
//                    "</html>"
//            );
//        }
//
//        // Method to handle POST method request.
//        public void doPost(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            doGet(request, response);
//        }
//    }
//    For the above example, it would display following result −
//
//    Reading Checkbox Data
//
//    Maths Flag : : on
//    Physics Flag: : null
//    Chemistry Flag: : on
//
//    Reading All Form Parameters
//
//    Following is the generic example which uses getParameterNames() method of HttpServletRequest to read all the available form parameters. This method returns an Enumeration that contains the parameter names in an unspecified order
//
//    Once we have an Enumeration, we can loop down the Enumeration in standard way by, using hasMoreElements() method to determine when to stop and using nextElement() method to get each parameter name.
//
//// Import required java libraries
//            import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.util.*;
//
//    // Extend HttpServlet class
//    public class ReadParams extends HttpServlet {
//
//        // Method to handle GET method request.
//        public void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            // Set response content type
//            response.setContentType("text/html");
//
//            PrintWriter out = response.getWriter();
//            String title = "Reading All Form Parameters";
//            String docType =
//                    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//            out.println(docType +
//                            "<html>\n" +
//                            "<head><title>" + title + "</title></head>\n" +
//                            "<body bgcolor = \"#f0f0f0\">\n" +
//                            "<h1 align = \"center\">" + title + "</h1>\n" +
//                            "<table width = \"100%\" border = \"1\" align = \"center\">\n" +
//                            "<tr bgcolor = \"#949494\">\n" +
//                            "<th>Param Name</th>"
//                    "<th>Param Value(s)</th>\n"+
//                            "</tr>\n"
//            );
//
//            Enumeration paramNames = request.getParameterNames();
//
//            while(paramNames.hasMoreElements()) {
//                String paramName = (String)paramNames.nextElement();
//                out.print("<tr><td>" + paramName + "</td>\n<td>");
//                String[] paramValues = request.getParameterValues(paramName);
//
//                // Read single valued data
//                if (paramValues.length == 1) {
//                    String paramValue = paramValues[0];
//                    if (paramValue.length() == 0)
//                        out.println("<i>No Value</i>");
//                    else
//                        out.println(paramValue);
//                } else {
//                    // Read multiple valued data
//                    out.println("<ul>");
//
//                    for(int i = 0; i < paramValues.length; i++) {
//                        out.println("<li>" + paramValues[i]);
//                    }
//                    out.println("</ul>");
//                }
//            }
//            out.println("</tr>\n</table>\n</body></html>");
//        }
//
//        // Method to handle POST method request.
//        public void doPost(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//
//            doGet(request, response);
//        }
//    }
//    Now, try the above servlet with the following form −
//
//<html>
//   <body>
//      <form action = "ReadParams" method = "POST" target = "_blank">
//         <input type = "checkbox" name = "maths" checked = "checked" /> Maths
//            <input type = "checkbox" name = "physics"  /> Physics
//            <input type = "checkbox" name = "chemistry" checked = "checked" /> Chem
//            <input type = "submit" value = "Select Subject" />
//      </form>
//   </body>
//</html>
//    Now calling servlet using the above form would generate the following result −
//
//    Reading All Form Parameters
//
//    Param Name	Param Value(s)
//    maths	on
//    chemistry	on
//}