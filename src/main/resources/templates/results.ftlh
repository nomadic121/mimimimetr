<#import "parts/basePage.ftlh" as c>

<@c.page>
    <h5>Результаты голосования</h5>
<#--    <div class="card-deck">-->
    <#list cats as cat>
        <div class="card">
            <img class="card-img-top" src="${cat.image}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${cat.name}</h5>
                <p class="card-text">${cat.message}</p>
            </div>
            <#--                <div class="card-footer"><small class="text-muted">Last updated 3 mins ago</small></div>-->
        </div>
    </#list>
<#--    </div>-->
</@c.page>