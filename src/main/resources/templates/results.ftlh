<#import "parts/basePage.ftlh" as c>

<@c.page>
    <#if msg??>
        <h5 class="text-center py-3">${msg}</h5>
    <#else>
        <h5 class="text-center py-3">Выбери своего любимого котика)</h5>
        <h5 class="text-center py-3">Результаты голосования</h5>
        <#list cats as cat>
            <div class="container py-3">
                <div class="card text-center mx-auto" style="width: 28rem;">
                    <img class="card-img-top" src="${cat.catDto.image}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${cat.catDto.name}</h5>
                        <p class="card-text">Голосов : ${cat.voteCount}</p>
                    </div>
                </div>
            </div>
        </#list>
    </#if>
    <div class="text-center py-3">
        <a href="/vote">Голосовать тут</a>
    </div>
</@c.page>
