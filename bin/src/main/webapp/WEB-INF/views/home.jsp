<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>
var xhr = new XMLHttpRequest();

var url = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst'; /*URL*/
var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'6zZfFkasEkgLqsokGB8s5EZ7zgXxdShQ7hMidHRXBSW9fIKQ2LscXjWwOBmkp%2FABrBLbxecMfVS1x7yh9653DQ%3D%3D'; /*Service Key*/
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('1000'); /**/
queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('json'); /**/
queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent('20230623'); /**/
queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent('0600'); /**/
queryParams += '&' + encodeURIComponent('nx') + '=' + encodeURIComponent('55'); /**/
queryParams += '&' + encodeURIComponent('ny') + '=' + encodeURIComponent('127'); /**/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
    	const jsonResponse = 'Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders()+'nBody: '+this.responseText);
        console.log('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
	    const responseData = JSON.parse(jsonResponse);
        const resultCode = responseData.response.header.resultCode;
        const resultMsg = responseData.response.header.resultMsg;
        const items = responseData.response.body.items.item;
        console.log("Result Code:", resultCode);
        console.log("Result Message:", resultMsg);

        items.forEach(item => {
        console.log("Category:", item.category);
         console.log("Value:", item.obsrValue);
        });
    }
};

xhr.send('');
</script>

