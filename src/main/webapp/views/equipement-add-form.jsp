<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Equipement
                App</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Equipements</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${equipement != null}">
            <form action="/update" method="post">
                </c:if>
                <c:if test="${equipement == null}">
                <form action="/insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${equipement != null}">
                                Edit Todo
                            </c:if>
                            <c:if test="${equipement == null}">
                                Add New Todo
                            </c:if>
                        </h2>
                    </caption>

                    <fieldset class="form-group">
                        <label>Equipement Status</label>
                        <select class="form-control" name="etat">
                            <option value="DISPONIBLE">DISPONIBLE</option>
                            <option value="INDISPONIBLE">INDISPONIBLE</option>
                        </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Equipement Site</label>
                        <select class="form-control" name="site">
                            <option value="CENTRE">CENTRE</option>
                            <option value="NORD">NORD</option>
                            <option value="SUD">SUD</option>
                        </select>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>