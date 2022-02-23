<#import "parts/common.ftlh" as c>
<@c.page>
    <div align=center>
        <h1>Welcome to clothing and shoes store</h1>
    </div>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
    <a href="/clothing" id="clothing"><h3>CLOTHING</h3></a>
    <a href="/shoes" id="shoes"><h3>SHOES</h3></a>
    <a href="/user" id="user"><h3>USER_LIST</h3></a>
    <a href="/adminPanel" id="user"><h3>ADMIN PANEL</h3></a>
</@c.page>

