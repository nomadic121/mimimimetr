<#import "parts/basePage.ftlh" as bp>

<@bp.page>
    <h5>Hello, guest</h5>
    <div class="card-deck">
        <#list cats as cat>
            <div class="card">
                <img class="card-img-top" src="${cat.image}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${cat.name}</h5>
                    <p class="card-text">${cat.message}</p>
                    <form action="/vote" method="post">
<#--                        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <input type="hidden" name="like" value="${cat.id}" />
<#--                        <input type="hidden" name="opponent" value="${cat.opponent}" />-->
                        <button class="btn btn-primary" type="submit">Like</button>
                    </form>

<#--                    <a methods="post" href="/vote/${cat.id}" class="btn btn-primary">Like</a>-->
                </div>
                <#--                <div class="card-footer"><small class="text-muted">Last updated 3 mins ago</small></div>-->
            </div>
        </#list>
    </div>
</@bp.page>