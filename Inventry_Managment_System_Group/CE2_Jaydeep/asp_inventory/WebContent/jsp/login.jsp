<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">Please Login To Enter </h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                <form:form name="loginform" id="loginform" method="post" action="logincheck.htm">
                   <h4> Login with facebook <strong> / </strong>Google :</h4>
                    <br />
                    <a href="index.html" class="btn btn-social btn-facebook">
                            <i class="fa fa-facebook"></i>&nbsp; Facebook Account</a>
                    &nbsp;OR&nbsp;
                    <a href="index.html" class="btn btn-social btn-google">
                            <i class="fa fa-google-plus"></i>&nbsp; Google Account</a>
                    <hr />
                     <h4> Or Login with <strong>your Account  :</strong></h4>
                    <br />
                     <label>Enter Email ID : </label>
                        <input type="text" name="emailid" class="form-control" required="required"/>
                        <label>Enter Password :  </label>
                        <input type="password" name="password" class="form-control" required="required"/>
                        <hr />
                        <input type="submit" class="btn btn-default"/>
                        </form:form>
                </div>
                <div class="col-md-6">
                    <div class="alert alert-info">
                        This is a free bootstrap admin template with basic pages you need to craft your project. 
                        Use this template for free to use for personal and commercial use.
                        <br />
                         <strong> Some of its features are given below :</strong>
                        <ul>
                            <li>
                                Responsive Design Framework Used
                            </li>
                            <li>
                                Easy to use and customize
                            </li>
                            <li>
                                Font awesome icons included
                            </li>
                            <li>
                                Clean and light code used.
                            </li>
                        </ul>
                       
                    </div>
                    <div class="alert alert-success">
                         <strong> Instructions To Use:</strong>
                        <ul>
                            <li>
                               Lorem ipsum dolor sit amet ipsum dolor sit ame
                            </li>
                            <li>
                                 Aamet ipsum dolor sit ame
                            </li>
                            <li>
                               Lorem ipsum dolor sit amet ipsum dolor
                            </li>
                            <li>
                                 Cpsum dolor sit ame
                            </li>
                        </ul>
                       
                    </div>
                </div>
            </div>
        </div>
