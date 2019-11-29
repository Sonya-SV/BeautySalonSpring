<#macro pager url page >
    <center>
        <div class="row sm ml-0">
            <ul class="pagination pagination-sm mb-1">
                <#list 1..page.getTotalPages() as p>
                    <#if (p-1)==page.getNumber()>
                        <li class="page-item active">
                            <a class="page-link" href="#" tabindex="-1">${p}</a>
                        </li>
                    <#else>
                        <li class="page-item">
                            <a class="page-link" href="${url}?page=${p-1}&size=${page.getSize()}"
                               tabindex="-1">${p}</a>
                        </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </center>
</#macro>