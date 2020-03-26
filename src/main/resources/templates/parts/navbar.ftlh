<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Mimimimetr</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/vote">Голосование</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/results">Результаты</a>
            </li>
<#--            <#if isAdmin>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/userEntity">Список пользователей</a>-->
<#--                </li>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/addCats">Добавить котов</a>-->
<#--                </li>-->
<#--            </#if>-->
            <#--            <#if userEntity??>-->
<#--                <li class="nav-item">-->
            <#--                    <a class="nav-link" href="/userEntity/profile">Редактировать профиль</a>-->
<#--                </li>-->
<#--            </#if>-->
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </div>
</nav>