let $price := for $x in doc("src//employee.xml")/employees/employee/project return number($x/price)
return <TotalProductPrice>${sum($price)}.00</TotalProductPrice>
