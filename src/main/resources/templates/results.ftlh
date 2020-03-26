<#import "parts/basePage.ftlh" as c>

<@c.page>
    <h5 class="text-center py-3">Результаты голосования</h5>
<#--    <div class="card-deck">-->
    <#list cats as cat>
        <dvi class="p-3">
            <div class="card text-center">
                <img class="card-img-top" src="${cat.image}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${cat.name}</h5>
                    <p class="card-text">Голосов : ${cat.voteCount}</p>
                </div>
            </div>
        </dvi>
    </#list>
<#--    </div>-->
</@c.page>