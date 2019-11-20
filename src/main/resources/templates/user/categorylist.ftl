<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<div class="container" style="margin-top: 150px">
    <form autocomplete="off" novalidate>
        <div class="menu">
        <#list categories as i >
            <div class="holder">
                <a href="/user/procedures/${i.id}">
                    <img src="data:image/png;base64,${i.base64}" alt="..." height="380">
                    <input type="hidden" value="${i.id}" name="categoryId">
                </a>
                <div class="block">
                    <h2 style="text-align: center">${i.name}</h2>
                </div>
            </div>
        </#list>
        </div>
    </form>
</div>
</@c.page>