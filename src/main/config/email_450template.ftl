<html>
<head>
<style type="text/css">
    body {
        font: Arial;
        font-size: 12px;
    }
    td {        
        text-align:left;
        font: Arial;
        font-size: 12px;
    }
    th {        
        text-align:left;
        font: Arial;
        font-size: 12px;
    }    
</style>

</head>
<body>

<p>
Dear ${headOfHouseHold["CensusDetails.Title"]} ${headOfHouseHold.Forename} ${headOfHouseHold["CensusDetails.Surname"]},<br/><br/>
</p>
<p>We thank you for having completed the OAUK Census/Directory Form 2009.<br/><br/>
We are now in the process of verifying the data in readiness for finalising the directory content. <br/><br/>
The information that we have on your household and which will be included in the directory is shown below. We will be obliged if you would take some time to review the information to ensure that it is accurate.
In addition, we have now provided the facility to include contact details for other household OAUK members (work telephone number, mobile and e-mail address). If you want these details to be included for any of your household members and/or if there are any errors, please notify us immediately by writing to <a href="mailto:oaukdirectory_help@oaukintranet.org?subject=OAUK_Census_and_Directory_Form_2009">oaukdirectory_help@oaukintranet.org</a> and providing us with the additional details or the rectifications required.<b><u>Please DO NOT contact the office as they will not be able to amend the data.</u></b><br/><br/>
</p><p><br/>
<b><u>DEADLINE TO RESPOND</u></b> - Please ensure that you have responded by <b><u>14th of March 2010</u></b>.  After this date, the data will be submitted as shown unless we have received your e-mail requesting rectification by the deadline.
<br/><br/>
This e-mail is being sent to all households that have completed the OAUK Census/Directory Form 2009 and who have provided their contact e-mail address.<br/><br/>
We have recently received reports that some other websites have had links to the OAUK Census Form and people have inadvertently completed forms not associated to OAUK.  The OAUK Census and Directory Form 2009 has only been available through <a href="http://www.oshwal.org/">www.oshwal.org</a>. You have completed the correct form.  However, if you know of anyone who has not received a similar verification e-mail from us, please request them to contact us as a matter of urgency at the above e-mail address.<br/><br/>
As you will appreciate, in order for the directory to be successful, it is imperative that it is as complete as possible and has the correct information. Your assistance in spreading the word would therefore be greatly appreciated.<br/><br/>
</p>
<p>
Yours sincerely,<br/>
    The Database and Directory Committee.
</p>

<h3>Form Data:</h3>
        <h4>Primary House Hold Contact:</h4>
<table>
                <tr><th>Title :</th><td> ${headOfHouseHold["CensusDetails.Title"]!}</td></tr>
                <tr><th>Surname :</th><td> ${headOfHouseHold["CensusDetails.Surname"]!}</td></tr>
                <tr><th>Gender :</th><td> ${headOfHouseHold["Gender"]!}</td></tr>
                <tr><th>Forename :</th><td> ${headOfHouseHold["Forename"]!}</td></tr>
                <tr><th>Fathers Name :</th><td> ${headOfHouseHold["FathersName"]!}</td></tr>
                <tr><th>Grandfathers Name :</th><td> ${headOfHouseHold["GrandfathersName"]!}</td></tr>
                <tr><th>Husbands Name :</th><td> ${headOfHouseHold["HusbandsName"]!}</td></tr>
                <tr><th>Occupation :</th><td> ${headOfHouseHold["Occupation"]!}</td></tr>
                <tr><th>Qualification :</th><td> ${headOfHouseHold["Qualification"]!}</td></tr>
                <tr><th>
                <tr><th>Attak / Nukh :</th><td> ${headOfHouseHold["Attuk"]!}</td></tr>
                <tr><th>Name of Village in India :</th><td> ${headOfHouseHold["CensusHHDetails.Village"]!}</td></tr>
                <tr><th>Country of Residence prior to UK :</th><td> ${headOfHouseHold["CountryPrior"]!}</td></tr>
                <tr><th>Town of Residence Prior to UK :</th><td> ${headOfHouseHold["TownPrior"]!}</td></tr>
                <tr><th>OAUK Area (if Known) :</th><td> ${headOfHouseHold["CensusHHDetails.OAUKArea"]!}</td></tr>
                <tr/>
                <tr><th>Address - Flat Number or name :</th><td> ${headOfHouseHold["AddFlat"]!}</td></tr>
                <tr><th>Address - House Number :</th><td> ${headOfHouseHold["AddHouse"]!}</td></tr>
                <tr><th>Address - Street Name :</th><td> ${headOfHouseHold["AddStreet"]!}</td></tr>
                <tr><th>Address - Town :</th><td> ${headOfHouseHold["AddTown"]!}</td></tr>
                <tr><th>Address - County :</th><td> ${headOfHouseHold["AddCounty"]!}</td></tr>
                <tr><th>Address - Post code :</th><td> ${headOfHouseHold["AddPostCode"]!}</td></tr>
                <tr><th>Home Telephone No (incl STD Code) :</th><td> ${headOfHouseHold["CensusHHDetails.HomeTel"]!}</td></tr>
                <tr><th>Mobile Telephone No :</th><td> ${headOfHouseHold["Mobile"]!}</td></tr>
                <tr><th>Work Telephone No (incl STD Code) :</th><td> ${headOfHouseHold["WorkTel"]!}</td></tr>
                <tr><th>E-Mail  :</th><td> ${headOfHouseHold["CensusDetails.Email"]!}</td></tr>
                <tr><th>Publish E-Mail in directory :</th><td> ${headOfHouseHold["PublishEmail"]!}</td></tr>
                <tr/>
                <tr><th>Business Name :</th><td> ${headOfHouseHold["BusinessName"]!}</td></tr>
                <tr><th>Business classification :</th><td> ${headOfHouseHold["BusinessClassification"]!}</td></tr>
                <tr><th>Business Tel (incl STD Code) :</th><td> ${headOfHouseHold["BusinessTel"]!}</td></tr>
                <tr><th>Business E Mail :</th><td> ${headOfHouseHold["BusinessEmail"]!}</td></tr>
                <tr><th>Business Web site :</th><td> ${headOfHouseHold["BusinessWeb"]!}</td></tr>
    </table>
<br/><br/>
     <#list houseHold as member>
     <p><h4>House Hold Member [${member_index + 2}]:</h4></p>
<p>
    <table>
                <tr><th>Title :</th><td> ${member["CensusDetails.Title"]!}</td></tr>
                <tr><th>Surname :</th><td> ${member["CensusDetails.Surname"]!}</td></tr>
                <tr><th>Gender :</th><td> ${member["Gender"]!}</td></tr>
                <tr><th>Forename :</th><td> ${member["Forename"]!}</td></tr>
                <tr><th>Fathers Name :</th><td> ${member["FathersName"]!}</td></tr>
                <tr><th>GrandFathers Name :</th><td> ${member["GrandfathersName"]!}</td></tr>
                <tr><th>Husbands Name :</th><td> ${member["HusbandsName"]!}</td></tr>
                <tr><th>Occupation   :</th><td> ${member["Occupation"]!}</td></tr>
                <tr><th>Qualifications :</th><td> ${member["Qualification"]!}</td></tr>
                <tr><th>Relation to Head of Household  :</th><td> ${member["CensusDetails.Relation"]!}</td></tr>
                <tr><th>E-Mail  :</th><td> ${member["CensusDetails.Email"]!}</td></tr>
                <tr><th>Mobile Telephone No :</th><td> ${member["Mobile"]!}</td></tr>
                <tr><th>Work Telephone No :</th><td> ${member["WorkTel"]!}</td></tr>
    </table>
 </p><br/><br/>
     </#list>

<p>
==================================================================================
Before printing think about your responsibility & commitment to the environment!
==================================================================================
</p>
</body>
</html>
