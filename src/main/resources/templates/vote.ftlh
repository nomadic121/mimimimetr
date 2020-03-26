<#import "parts/basePage.ftlh" as bp>

<@bp.page>
    <#if msg??>
        <h5 class="text-center py-3">${msg}</h5>
    <#else>
        <h5 class="text-center py-3">Выбери своего любимого котика)</h5>
    </#if>
    <div class="card-deck">
        <#if catVoteForm??>
            <div class="card text-center">
                <img class="card-img-top" src="${catVoteForm.cat1.image}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${catVoteForm.cat1.name}</h5>
                    <p class="card-text">${catVoteForm.cat1.message}</p>
                    <form action="/vote" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="like" value="${catVoteForm.cat1.id}"/>
                        <input type="hidden" name="unLike" value="${catVoteForm.cat2.id}"/>
                        <button class="btn btn-primary" type="submit">Like</button>
                    </form>
                </div>
            </div>
            <div class="card text-center">
                <img class="card-img-top" src="${catVoteForm.cat2.image}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${catVoteForm.cat2.name}</h5>
                    <p class="card-text">${catVoteForm.cat2.message}</p>
                    <form action="/vote" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="like" value="${catVoteForm.cat2.id}"/>
                        <input type="hidden" name="unLike" value="${catVoteForm.cat1.id}"/>
                        <button class="btn btn-primary" type="submit">Like</button>
                    </form>
                </div>
            </div>
        </#if>
    </div>
</@bp.page>