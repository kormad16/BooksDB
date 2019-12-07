<%-- 
    Document   : bookShop
    Created on : 26.11.2019, 14:02:35
    Author     : KORNBERGERMarc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/nightmode.js"></script>
        <title>Book Shop</title>
    </head>
    <body onload="initNightmode()">
        <button onclick="swapMode()" id="nightmodeButton">Turn off the lights</button>
        <h1 id="title">Welcome to the Online Book Store!</h1>
        <form method="get" style="text-align: center;">
            <input type="hidden" name="sort" value="${requestScope.sort}">
            <input style="width:25%;" class="input" type="text" name="filter" placeholder="Filter books by title or author" value="${requestScope.filter}">
            <input class="input" type="submit" value="Search">
        </form>
        <table id="bookTable">
            <tr>
                <th><a class="link" href="?sort=title">Book Title</a></th>
                <th><a class="link" href="?sort=author">Author(s)</a></th>
                <th><a class="link" href="?sort=price">Price</a></th>
            </tr>
            <c:forEach items="${requestScope.books}" var="book">
                <tr>
                    <td class="title"><b>${book.title}</b> (ISBN: ${book.isbn})</td>
                    <td>
                        <c:forEach items="${book.getAuthors()}" var="author">
                            ${author["firstname"]} ${author["lastname"]}<br>
                        </c:forEach>
                    </td>
                    <td>€ ${String.format("%.2f",book["price"])}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
