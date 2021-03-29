<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<h3>student Registration</h3>
<br>
<form action='/index/add' method='post'>
 
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>Name</b></td> 
            <td><input type='text' name='name' class='form-control'  required/></td>
        </tr>
 
        <tr>
            <td><b>Surname</b></td>
            <td><input type='text' name='surname' class='form-control' required /></td>
        </tr>
 
        <tr>
            <td><b>Adress</b></td>
            <td><input type='text' name='adress' class='form-control' size="20" required/></td>
            
        </tr>
 
 
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Register</button>
            </td>
        </tr>
 
    </table>
    <b><c:out value="${danger}"></c:out></b>
</form>



<h3>List Of students</h3>
<br>
<table class="table table-hover">

    <thead>
      <tr>
        <th><b>student Name</b></th>
        <th><b>student Surname</b></th>
        <th><b>student Adress</b></th>
        <th><b>Transactions</b></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="lou">
      <tr>
        <td><c:out value="${lou.name}"></c:out></td>
        <td><c:out value="${lou.surname}"></c:out></td>
        <td><c:out value="${lou.adress}"></c:out></td>

			 <td>
             <a href="/index/${lou.id}/edit">
                <button type="submit" class="btn btn-primary">Edit student</button>
                </a>
            </td>
            <td>
             <a href="/index/${lou.id}/delete">
                <button type="submit" class="btn btn-primary">Delete student</button>
                </a>
            </td>
      </tr>

          </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="footer.jsp"></jsp:include>