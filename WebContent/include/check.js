function checkLength(strTemp)
{
	var i,sum;
	sum = 0;
	for(i=0;i<strTemp.length;i++)
	{
		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=128))
			sum = sum + 1;
		else
			sum = sum + 2;
	}
	return sum;
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
///    <td align="right"><a href="#" onclick="MM_openBrWindow('sample.jsp', '', 'scrollbars=yes,resizable=yes,width=700,height=450,center=yes')">帮助</a></td>
//    MM_openBrWindow('register.jsp','popup','scrollbars=yes,width=800,height=600');
