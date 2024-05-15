<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="ISO-8859-1">
                    <!-- for Bootstrap CSS -->
                    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
                    <!-- YOUR own local CSS -->
                    <link rel="stylesheet" href="/css/main.css" />
                    <!-- For any Bootstrap that uses JS or jQuery-->
                    <script src="/webjars/jquery/jquery.min.js"></script>
                    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

                    <title>edit show</title>
                </head>

                <body>
                    <div class="container my-5">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h1>Create a New Show</h1>
                            </div>

                        </div>
                        <div class="row mx-5">
                            <div class="col-9">

                                <form:form action="/show/${id}/edit" method="post" modelAttribute="Show">
                                    <input type="hidden" name="_method" value="put">
                                    <div class="form-group my-3">
                                        <form:errors path="title" class="text-danger" />
                                        <form:label path="title">Title</form:label>
                                        <form:input path="title" class="form-control" />
                                    </div>
                                    <div class="form-group my-3">
                                        <form:errors path="network" class="text-danger" />
                                        <form:label path="network">Network</form:label>
                                        <form:input path="network" class="form-control" />
                                    </div>
                                    <div class="form-group my-3">
                                        <form:errors path="description" class="text-danger" />
                                        <form:label path="description">Description</form:label>
                                        <form:textarea path="description" class="form-control" />
                                    </div>

                                    <div class="form-group my-3 d-flex justify-content-between">
                                        <a href="/shows" class="btn btn-danger">Cancel</a>

                                        <input type="submit" value="Submit" class="btn btn-primary " />


                                    </div>
                                </form:form>

                                <div class="d-flex justify-content-start">
                                    <c:if test="${tvshow.user.id == User.id}">
                                        <form action="/Show/${tvshow.id}" method="post">
                                            <input type="hidden" name="_method" value="delete">
                                            <input type="submit" value="Delete Show" class="btn btn-danger">
                                        </form>
                                    </c:if>
                                </div>

                            </div>
                            <div class="col-6">

                            </div>
                        </div>
                    </div>
                </body>

                </html>