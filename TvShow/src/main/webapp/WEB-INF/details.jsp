<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <!-- for Bootstrap CSS -->
                <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
                <!-- YOUR own local CSS -->
                <link rel="stylesheet" href="/css/main.css" />
                <!-- For any Bootstrap that uses JS or jQuery-->
                <script src="/webjars/jquery/jquery.min.js"></script>
                <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

                <title>Show info</title>
            </head>

            <body>
                <div class="container my-5">
                    <div class="d-flex justify-content-between align-items-center my-5">

                        <h1>
                            <c:out value="${show.title}" />
                        </h1>

                        <a href="/shows">back to Dashboard</a>
                    </div>
                    <div class="my-5">
                        <table class="table table-borderless">
                            <tr class="h5">
                                <td>Post by:</td>
                                <td>
                                    <c:out value="${show.user.name}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Network:</td>
                                <td>
                                    <c:out value="${show.network}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Description:</td>
                                <td>
                                    <c:out value="${show.description}" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <c:if test="${show.user.id == User.id}">
                                        <a href="/show/${show.id}/edit" class="btn btn-info">Edit</a>
                                    </c:if>
                                </td>
                            </tr>

                        </table>

                        <hr>


                        <table class="table table-bordered table-striped text-center">
                            <tr class="table-secondary">
                                <th>Name</th>
                                <th>Rating</th>
                            </tr>
                            <c:forEach var="rating" items="${ratings}">

                                <tr>
                                    <td>
                                        <c:out value="${rating.creator.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${rating.ranking}" />
                                    </td>
                                </tr>

                            </c:forEach>
                        </table>

                        <form:form action="/show/${id}/rate" method="post" modelAttribute="rating">
                            <div class="form-group row">

                                <form:label path="ranking" class="col-sm-2 col-form-label">Leave a Rating:
                                </form:label>
                                <div class="col-sm-10">
                                    <form:input path="ranking" class="form-control col-sm-10" />
                                </div>
                                <form:errors path="ranking" class="text-danger" />
                            </div>

                            <input type="submit" value="Rate!" class="btn btn-info my-3">
                        </form:form>

                    </div>






                </div>

            </body>

            </html>