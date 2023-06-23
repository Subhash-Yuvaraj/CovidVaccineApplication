<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Add Slot</title>
    <link rel="stylesheet" type="text/css" href="/css/addSlot.css">
    <script src="/js/addSlot.js"></script>
</head>
<body>
    <script>
       function getDateString(ele){
    	   const dateText = ele.innerText;
    	   const currDate = new Date(dateText);
    	   return currDate.toLocaleDateString();
       }
       function onSubmitHandler(){
    	   //Check for non empty input
    	   const inputDateText = document.getElementById("date").value;
    	   if(inputDateText==="") return;
    	   //Check whether date is already available
    	   const inputDate = new Date(inputDateText).toLocaleDateString();
    	   const dateElements = Array.from(document.getElementsByClassName("slotDates"));
    	   const dates = dateElements.map(getDateString);
    	   if(dates.includes(inputDate)){
    		   window.alert("The slot is already available for the centre");
    		   return;
    	   }
    	   const cId = document.getElementById("cId").value;
    	   const data = new URLSearchParams();
    	   data.append("cId",cId);
    	   data.append("date",inputDate.split("/").reverse().join("-"));
    	   fetch("/saveSlot", {
    		   method: "POST",
    		   body: data
    	   }).then(response=>{
    		   if(response.status===200) window.location.reload();
    		   else window.alert("Error in adding slots");
    	   })
       }
    </script>
    <h1>Add Slot</h1>
    <form onsubmit="return false" id="slot-form">
        <input type="hidden" id="cId" name="cId" value="${cId}">
        <label for="date">Date</label>
        <input type="date" name="date" id="date" min="<%= java.time.LocalDate.now().plusDays(1) %>" required><br><br>
        <button onclick="onSubmitHandler()">Add Slot</button><br><br>
    </form>
    <table>
        <tr>
        	<th>Slot Id</th>
            <th>Date</th>
            <th>People booked</th>
        </tr>
        <c:forEach var="slot" items="${slots}">
            <tr>
                <td>${slot.sId}</td>
                <td class="slotDates">${slot.date}</td>
                <td>${slot.bookings.size()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
