<#import "parts/basePage.ftlh" as bp>

<@bp.page>
    <#if msg??>
        <h5 class="text-center py-3">${msg}</h5>
    <#else>
        <h5 class="text-center py-3">Выбери своего любимого котика)</h5>
    </#if>
    <div class="card-deck">
        <#if cats??>
            <#list cats as cat>
                <div class="card text-center">
                    <img class="card-img-top" src="${cat.image}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${cat.name}</h5>
                        <p class="card-text">${cat.message}</p>
                        <form action="/vote" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="like" value="${cat.id}"/>
                            <input type="hidden" name="pair" value="${pair}"/>
                            <button class="btn btn-primary" type="submit">Like</button>
                        </form>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</@bp.page>