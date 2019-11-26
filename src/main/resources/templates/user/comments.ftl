<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#if comments??>
    <#list comments as com>
        <ul class="media-list">
            <li class="media">
                <div class="media-left">
                    <a href="#">
                        <img src="https://www.domzamkad.ru/images/no-avatar.png"
                             class="media-object img-circle"
                             alt="avatar" height="60">
                    </a>
                </div>
                <div class="media-body">
                    <div class="medi-heading">
                        <div class="autor"><h4>${com.user.firstName!} ${com.user.lastName!}</h4>
                        </div>
                        <div class="time" style="text-align: right">
                            <h4>${ com.dateTime?date("yyyy-MM-dd'T'HH:mm")?string("yyyy-MM-dd HH:mm") }</h4></div>
                         </div>
                        <div class="media-text text-justify"><p
                                    style="text-indent: 25px;">${com.comment!}</p>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="media-body">
                                    <textarea class="form-control" name="comments" maxlength="280"
                                              placeholder="Join the discussion"></textarea>
                            </div>
                        </div>
                    </div>
            </li>
        </ul>
        <hr align="center" width="100%" style="border-color: slategrey"/>
    </#list>
</#if>
