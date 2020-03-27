<#import "parts/basePage.ftlh" as c>

<@c.page>
    <h5 class="text-center py-3">Результаты голосования</h5>
    <div class="mx-auto" style="width: 28rem;>
        <#list cats as cat>
            <dvi class="p-3">
                <div class="card text-center" style="width: 28rem;">
                    <img class="card-img-top" src="${cat.catDto.image}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${cat.catDto.name}</h5>
                        <p class="card-text">Голосов : ${cat.voteCount}</p>
                    </div>
                </div>
            </dvi>
        </#list>
    </div>
    <div class="text-center py-3">>
    <a href="/vote">Голосовать тут</a>
    </div>
</@c.page>