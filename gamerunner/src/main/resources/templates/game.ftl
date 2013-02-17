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
            <table>
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
                                       <td width=28 height=28 id='gba${col_index}' class="br">${row[col_index]}</td>
                                   </#list>
                                   <td width=28 height=28 class=pts>${firstPlayerScore[0][row_index]}</td>
                               </tr>
                            </#list>
                            <tr>
                                <#list game.firstPlayerBoard as row>
                                    <td width=36 height=28 class=pts>${firstPlayerScore[1][row_index]}</td>
                                </#list>
                                <td width=36 height=28 class=pts>${firstPlayerTotalScore}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <form>
                            <select name='movesel' size="15" style='font-family: monospace'
                                      onchange='gSetMove(this.selectedIndex+1)'>
                            <option value="0">&nbsp;1.&nbsp;&nbsp;A&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6</option>
                            <option value="11">&nbsp;2.&nbsp;&nbsp;&nbsp;&nbsp;18&nbsp;&nbsp;&nbsp;Z&nbsp;&nbsp;0
                            </option>
                            <option value="2">&nbsp;3.&nbsp;&nbsp;J&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;44
                            </option>
                            <option value="31">&nbsp;4.&nbsp;&nbsp;&nbsp;&nbsp;27&nbsp;&nbsp;&nbsp;Y&nbsp;&nbsp;1
                            </option>
                            <option value="4">&nbsp;5.&nbsp;&nbsp;O&nbsp;&nbsp;45&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;17
                            </option>
                            <option value="51">&nbsp;6.&nbsp;&nbsp;&nbsp;&nbsp;37&nbsp;&nbsp;&nbsp;Z&nbsp;&nbsp;2
                            </option>
                            <option value="6">&nbsp;7.&nbsp;&nbsp;S&nbsp;&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;27
                            </option>
                            <option value="71">&nbsp;8.&nbsp;&nbsp;&nbsp;&nbsp;46&nbsp;&nbsp;&nbsp;Z&nbsp;&nbsp;3
                            </option>
                            <option value="8">&nbsp;9.&nbsp;&nbsp;X&nbsp;&nbsp;15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;35
                            </option>
                            <option value="91">&nbsp;10.&nbsp;&nbsp;&nbsp;&nbsp;32&nbsp;&nbsp;&nbsp;Y&nbsp;&nbsp;4
                            </option>
                            <option value="10">&nbsp;11.&nbsp;&nbsp;Q&nbsp;&nbsp;42&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;31
                            </option>
                            <option value="111">&nbsp;12.&nbsp;&nbsp;&nbsp;&nbsp;11&nbsp;&nbsp;&nbsp;V&nbsp;&nbsp;5
                            </option>
                            <option value="12">&nbsp;13.&nbsp;&nbsp;F&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12
                            </option>
                            <option value="131">&nbsp;14.&nbsp;&nbsp;&nbsp;&nbsp;20&nbsp;&nbsp;&nbsp;Z&nbsp;&nbsp;7
                            </option>
                            <option value="14">&nbsp;15.&nbsp;&nbsp;K&nbsp;&nbsp;12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10
                            </option>
                            <option value="151">&nbsp;16.&nbsp;&nbsp;&nbsp;&nbsp;30&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;8
                            </option>
                            <option value="16">&nbsp;17.&nbsp;&nbsp;P&nbsp;&nbsp;47&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16
                            </option>
                            <option value="171">&nbsp;18.&nbsp;&nbsp;&nbsp;&nbsp;39&nbsp;&nbsp;&nbsp;N&nbsp;&nbsp;9
                            </option>
                            <option value="18">&nbsp;19.&nbsp;&nbsp;U&nbsp;&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;32
                            </option>
                            <option value="191">&nbsp;20.&nbsp;&nbsp;&nbsp;&nbsp;25&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;11
                            </option>
                            <option value="20">&nbsp;21.&nbsp;&nbsp;N&nbsp;&nbsp;17&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30
                            </option>
                            <option value="211">&nbsp;22.&nbsp;&nbsp;&nbsp;&nbsp;35&nbsp;&nbsp;&nbsp;Y&nbsp;&nbsp;13
                            </option>
                            <option value="22">&nbsp;23.&nbsp;&nbsp;S&nbsp;&nbsp;13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20
                            </option>
                            <option value="231">&nbsp;24.&nbsp;&nbsp;&nbsp;&nbsp;23&nbsp;&nbsp;&nbsp;Y&nbsp;&nbsp;14
                            </option>
                            <option value="24">&nbsp;25.&nbsp;&nbsp;L&nbsp;&nbsp;40&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;37
                            </option>
                            <option value="251">&nbsp;26.&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;15
                            </option>
                            <option value="26">&nbsp;27.&nbsp;&nbsp;A&nbsp;&nbsp;28&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;25
                            </option>
                            <option value="271">&nbsp;28.&nbsp;&nbsp;&nbsp;&nbsp;38&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;18
                            </option>
                            <option value="28">&nbsp;29.&nbsp;&nbsp;T&nbsp;&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;48
                            </option>
                            <option value="291">&nbsp;30.&nbsp;&nbsp;&nbsp;&nbsp;16&nbsp;&nbsp;&nbsp;K&nbsp;&nbsp;19
                            </option>
                            <option value="30">&nbsp;31.&nbsp;&nbsp;I&nbsp;&nbsp;43&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;22
                            </option>
                            <option value="311">&nbsp;32.&nbsp;&nbsp;&nbsp;&nbsp;21&nbsp;&nbsp;&nbsp;W&nbsp;&nbsp;21
                            </option>
                            <option value="32">&nbsp;33.&nbsp;&nbsp;K&nbsp;&nbsp;31&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;42
                            </option>
                            <option value="331">&nbsp;34.&nbsp;&nbsp;&nbsp;&nbsp;9&nbsp;&nbsp;&nbsp;G&nbsp;&nbsp;23
                            </option>
                            <option value="34">&nbsp;35.&nbsp;&nbsp;E&nbsp;&nbsp;36&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;40
                            </option>
                            <option value="351">&nbsp;36.&nbsp;&nbsp;&nbsp;&nbsp;33&nbsp;&nbsp;&nbsp;M&nbsp;&nbsp;26
                            </option>
                            <option value="36">&nbsp;37.&nbsp;&nbsp;R&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;34
                            </option>
                            <option value="371">&nbsp;38.&nbsp;&nbsp;&nbsp;&nbsp;48&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;28
                            </option>
                            <option value="38">&nbsp;39.&nbsp;&nbsp;Y&nbsp;&nbsp;26&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;36
                            </option>
                            <option value="391">&nbsp;40.&nbsp;&nbsp;&nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;O&nbsp;&nbsp;29
                            </option>
                            <option value="40">&nbsp;41.&nbsp;&nbsp;C&nbsp;&nbsp;14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;47
                            </option>
                            <option value="411">&nbsp;42.&nbsp;&nbsp;&nbsp;&nbsp;41&nbsp;&nbsp;&nbsp;E&nbsp;&nbsp;33
                            </option>
                            <option value="42">&nbsp;43.&nbsp;&nbsp;V&nbsp;&nbsp;19&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;46
                            </option>
                            <option value="431">&nbsp;44.&nbsp;&nbsp;&nbsp;&nbsp;29&nbsp;&nbsp;&nbsp;O&nbsp;&nbsp;38
                            </option>
                            <option value="44">&nbsp;45.&nbsp;&nbsp;O&nbsp;&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;45
                            </option>
                            <option value="451">&nbsp;46.&nbsp;&nbsp;&nbsp;&nbsp;34&nbsp;&nbsp;&nbsp;S&nbsp;&nbsp;39
                            </option>
                            <option value="46">&nbsp;47.&nbsp;&nbsp;R&nbsp;&nbsp;44&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;43
                            </option>
                            <option value="471">&nbsp;48.&nbsp;&nbsp;&nbsp;&nbsp;22&nbsp;&nbsp;&nbsp;S&nbsp;&nbsp;41
                            </option>
                        </select></form>
                    </td>
                    <td align='center'>
                        <small><b>${game.secondPlayer.name}</b></small>
                        <br>
                        <table cellspacing=0 cellpadding=0 class="gameboard">
                        <#list game.secondPlayerBoard as row>
                            <tr>
                                <#list row as col>
                                    <td width=28 height=28 id='gba${col_index}' class="br">${row[col_index]}</td>
                                </#list>
                                <td width=28 height=28 class=pts>${secondPlayerScore[0][row_index]}</td>
                            </tr>
                        </#list>
                            <tr>
                            <#list game.secondPlayerBoard as row>
                                <td width=36 height=28 class=pts>${secondPlayerScore[1][row_index]}</td>
                            </#list>
                                <td width=36 height=28 class=pts>${secondPlayerTotalScore}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <script type='text/javascript'>
                gposa = new Array(0, 18, 10, 27, 45, 37, 5, 46, 15, 32, 42, 11, 3, 20, 12, 30, 47, 39, 8, 25, 17, 35, 13, 23, 40, 1, 28, 38, 6, 16, 43, 21, 31, 9, 36, 33, 2, 48, 26, 4, 14, 41, 19, 29, 7, 34, 44, 22);
                gposb = new Array(6, 0, 44, 1, 17, 2, 27, 3, 35, 4, 31, 5, 12, 7, 10, 8, 16, 9, 32, 11, 30, 13, 20, 14, 37, 15, 25, 18, 48, 19, 22, 21, 42, 23, 40, 26, 34, 28, 36, 29, 47, 33, 46, 38, 45, 39, 43, 41);
                gInit();
            </script>
        </td>
    </tr>
</table>
</body>
</html>