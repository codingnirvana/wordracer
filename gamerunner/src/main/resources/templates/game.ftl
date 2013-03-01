<html>
<head>
    <title>Tournament - Game Results</title>
    <link rel="stylesheet" href="../style.css" type="text/css">
    <script type="text/javascript" src="../gameboard.js"></script>
</head>
<body>
<table border=0 cellspacing=0 cellpadding=4>
    <tr valign='top'>
        <td></td>
        <td align='left' width=750><h2>Game report</h2>
            <table id="gameReport" class='empty'>
                <tr>
                    <th align=left>Game:&nbsp;&nbsp;</th>
                    <td class=kimborder>${game.gameNumber}</td>
                </tr>
                <tr>
                    <th align=left>Player 1:&nbsp;&nbsp;</th>
                    <td class=kimborder>${game.firstPlayer.name}</td>
                </tr>
                <tr>
                    <th align=left>Player 2:&nbsp;&nbsp;</th>
                    <td class=kimborder>${game.secondPlayer.name}</td>
                </tr>
                <tr>
                    <th align=left>Status:&nbsp;&nbsp;</th>
                    <td class=kimborder>OK</td>
                </tr>
                <tr>
                    <th align=left>Result:&nbsp;&nbsp;</th>
                    <td class=kimborder>${game.gameResultAsString}</td>
                </tr>
            </table>
            <h3>Board position</h3>
            <table>
                <tr>
                    <td align='center'>
                        <small><b>${game.firstPlayer.name}</b></small>
                        <br>
                        <table cellspacing=0 cellpadding=0 class="gameboard">
                            <#list game.firstPlayerBoard as row>
                               <tr>
                                   <#list row as col>
                                       <td width=28 height=28 id='gba${7*row_index + col_index}' class="br">${row[col_index]}</td>
                                   </#list>
                                   <td width=28 height=28 class="pts empty">${firstPlayerScore[0][row_index]}</td>
                               </tr>
                            </#list>
                            <tr>
                                <#list game.firstPlayerBoard as row>
                                    <td width=36 height=28 class="pts empty">${firstPlayerScore[1][row_index]}</td>
                                </#list>
                                <td width=36 height=28 class="pts empty">${firstPlayerTotalScore}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <form>
                            <select name='movesel' id='movesel' size="15" style='font-family: monospace'
                                    onchange='gSetMove(this.selectedIndex+1)'>
                                <#list game.firstPlayerMoves as move>
                                    <option value="${move_index}">
                                        &nbsp;${move_index + 1}.
                                        &nbsp;&nbsp;${move.letter}&nbsp;&nbsp;${move.position}&nbsp;
                                        &nbsp;&nbsp;${game.secondPlayerMoves[move_index].letter}&nbsp;&nbsp;${game.secondPlayerMoves[move_index].position}
                                    </option>
                                </#list>
                            </select></form>
                    </td>
                    <td align='center'>
                        <small><b>${game.secondPlayer.name}</b></small>
                        <br>
                        <table cellspacing=0 cellpadding=0 class="gameboard">
                        <#list game.secondPlayerBoard as row>
                            <tr>
                                <#list row as col>
                                    <td width=28 height=28 id='gbb${7*row_index + col_index}' class="br">${row[col_index]}</td>
                                </#list>
                                <td width=28 height=28 class="pts empty">${secondPlayerScore[0][row_index]}</td>
                            </tr>
                        </#list>
                            <tr>
                            <#list game.secondPlayerBoard as row>
                                <td width=36 height=28 class="pts empty">${secondPlayerScore[1][row_index]}</td>
                            </#list>
                                <td width=36 height=28 class="pts empty">${secondPlayerTotalScore}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <script type='text/javascript'>
                gposa = [<#list game.firstPlayerMoves as move>${move.position},</#list>];
                gposb = [<#list game.secondPlayerMoves as move>${move.position},</#list>];
                gInit();
            </script>
        </td>
    </tr>
</table>
</body>
</html>