<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

                    <title>Tv Show</title>
                </head>

                <body>
                    <div class="container my-5">
                        <div class="">
                            <div class="w-50 d-flex justify-content-between align-items-center">
                                <h3 class="text-info">Welcome,
                                    <c:out value="${User.name}" />!
                                </h3>
                                <a href="/logout">logout</a>
                            </div>
                            <div class="d-flex justify-content-between align-items-center my-3">
                                <p>All Shows</p>


                            </div>
                        </div>

                        <table class="table table-bordered table-striped text-center">
                            <tr class="table-secondary">
                                <th>Show</th>
                                <th>Network</th>
                                <th>Average Rating</th>

                            </tr>
                            <c:forEach var="show" items="${shows}">

                                <tr>
                                    <td>
                                        <a href="/show/${show.id}">
                                            <c:out value="${show.title}" />

                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${show.network}" />
                                    </td>
                                    <td>
                                        <c:if test="${show.ratings.size()!=0}">
                                            <fmt:formatNumber type="number" maxFractionDigits="1"
                                                value="${function.count(show)}" />

                                        </c:if>
                                        <c:if test="${show.ratings.size()==0}">
                                            0
                                        </c:if>

                                    </td>
                                </tr>

                            </c:forEach>
                        </table>

                        <a href="/show/new" class="btn btn-info text-light">Add show</a>


                    </div>
                </body>

                </html>