<list>
{
for $x in doc("src//employee.xml")/employees/employee
where some $match in $x/hiredate satisfies
(contains ($match,"2005"))
return <hired>{data($x/firstname)}_{data($x/lastname)}</hired>
}
</list>
