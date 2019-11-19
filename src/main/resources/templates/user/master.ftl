<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-5">
                <img class="card-img" src="data:image/png;base64,${master.base64}" alt="..." height="400" style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
            </div>
            <div class="col-md-7">
                <h1 align="center" class="card-title">${master.user.firstName} ${master.user.lastName}</h1>
                <hr align="center" width="300"/>
                <p class="card-text">
                <h2><@spring.message "work.schedule"/> ${master.timeStart} - ${master.timeEnd}</h2> </p>
                <form action="user/booking">
                    <#if procedures ??>
                        <h2>${procedures[0].category.name}</h2>
                        <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                            <tr>
                                <th><@spring.message "name"/></th>
                                <th><@spring.message "price"/></th>
                                <th><<@spring.message "choose"/></th>
                            </tr>
                            <#if procedures??>
                                <#list procedures as i>
                                    <tr>
                                        <td>${i.name}</td>
                                        <td>${i.price}</td>
                                        <td>
                                            <button type="submit" class="btn btn-primary" style="margin-top:0px"
                                                   value="${i.id}" name="procedureId">
                                                <@spring.message "choose"/>
                                            </button>
                                        </td>
                                    </tr>

                                </#list>
                            </#if>
                        </table>
                    </#if>
                    <input type="hidden" value="${master.id}" name="masterId"/>
                </form>
            </div>
        </div>
    </div>

<#--<div class="container" style="margin-top: 60px">-->
<#--    <div class="col-md-10    col-md-offset-1" style="padding-top: 50px">-->
<#--        <h2 align="center"><fmt:message key="reviews"/></h2>-->
<#--        <div class="comments">-->
<#--            <form action="${pageContext.request.contextPath}/beauty-salon/user/comment">-->
<#--                <c:if test="${successSend ne null}">-->
<#--                    <div class="alert alert-success">-->
<#--                            ${successSend}-->
<#--                    </div>-->
<#--                </c:if>-->
<#--                <c:if test="${emptyComment ne null}">-->
<#--                    <div class="alert alert-danger">-->
<#--                            ${emptyComment}-->
<#--                    </div>-->
<#--                </c:if>-->
<#--                <textarea required rows="4" cols="100%" name="comment"-->
<#--                          placeholder="<fmt:message key="put.your.comment"/>"></textarea>-->
<#--                <input type="hidden" name="masterId" value="${master.id}">-->
<#--                <button type="submit" class="btn btn-primary" style="margin-top:30px"-->
<#--                        ng-disabled="form.$invalid">-->
<#--                    <fmt:message key="send"/>-->
<#--                </button>-->

<#--            </form>-->
<#--            <h5>${fn:length(comments)} <fmt:message key="comments"/></h5>-->
<#--            <c:forEach items="${comments}" var="com">-->
<#--                <ul class="media-list">-->
<#--                    <li class="media">-->
<#--                        <div class="media-left">-->
<#--                            <a href="#">-->
<#--                                <img src="https://www.domzamkad.ru/images/no-avatar.png" class="media-object img-circle"-->
<#--                                     alt="avatar" height="60">-->
<#--                            </a>-->
<#--                        </div>-->
<#--                        <div class="media-body">-->
<#--                            <div class="medi-heading">-->
<#--                                <div class="autor"><h4></h4>${com.user.firstName} ${com.user.lastName}</h4></div>-->
<#--                                <div class="time" style="text-align: right"><h4>-->
<#--                                    <fmt:parseDate value="${ com.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />-->
<#--                                    <fmt:formatDate pattern="dd MMM  yyyy HH:mm" value="${ parsedDateTime }"/></h4></div>-->
<#--                            </div>-->
<#--                            <div class="media-text text-justify"><p style="text-indent: 25px;">${com.comment}</p>-->
<#--                            </div>-->
<#--                            <div id="collapseOne" class="panel-collapse collapse">-->
<#--                                <div class="media-body">-->
<#--                                    <textarea class="form-control" name="comments" rows="3" maxlength="280"-->
<#--                                              placeholder="Join the discussion"></textarea>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </li>-->
<#--                </ul>-->
<#--                <hr align="center" width="100%" style="border-color: slategrey"/>-->
<#--            </c:forEach>-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

    <script>
        $(document).ready(function () {
            $("input:radio").click(function () {
                if ($(this).is(":checked")) {
                    $("label").css({"background-color": "transparent"}) &&
                    $(this).closest("label").css({"background-color": "lightskyblue"});
                }
            });
        });
    </script>
</@c.page>