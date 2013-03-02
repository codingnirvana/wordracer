<html>
<head>
    <title>Tournament - Results</title>
    <link rel="stylesheet" href="../style.css" type="text/css">
</head>
<body>
<table border="0" cellspacing="0" cellpadding="4">
    <tr valign="top">
        <td style="width: 24px"></td>
        <td align="left" width="750"><h2>Final Rankings </h2>
            <table border="0">
                <tr>
                    <th align="center" width="50" class="kimborder">Rank</th>
                    <th align="left" width="150" class="kimborder">Name</th>
                    <th align="left" width="150" class="kimborder">Score</th>
                    <th align="left" width="150" class="kimborder">Points</th>
                </tr>
                <#list rankings as ranking>
                <tr>
                    <td align="right" class="kimborder">${ranking_index + 1}</td>
                    <td class="kimborder">
                        <div class="gray">${ranking.player.name}</div>
                    </td>
                    <td class="kimborder">
                        <div>${ranking.totalScore}</div>
                    </td>
                    <td class="kimborder">${ranking.totalPoints}</td>
                </tr>
                </#list>
            </table>
            <br><br><br><br>
            <table border="0" cellspacing="0" cellpadding="4">
                <tr valign="top">
                    <td style="width: 24px"></td>
                    <td align="left" width="750"><h2>Match Details</h2>
                        <table border="0">
                            <tr>
                                <th align="center" width="50" class="kimborder">Game</th>
                                <th align="left" width="150" class="kimborder">Player A</th>
                                <th align="left" width="150" class="kimborder">Player B</th>
                                <th align="left" width="150" class="kimborder">Result</th>
                            </tr>
                            <#list games as game>
                                <tr>
                                    <td align="right" class="kimborder"><a href='game${game.gameNumber}.html'>${game.gameNumber}</a> &nbsp;</td>
                                    <td class="kimborder">
                                    <div>
                                    <#if game.result == "FIRST_PLAYER_WINNER" >
                                        <b>${game.firstPlayer.name}</b>
                                    <#else>
                                        ${game.firstPlayer.name}
                                    </#if>
                                    </div>
                                </td>
                                <td class="kimborder">
                                    <div>
                                    <#if game.result == "SECOND_PLAYER_WINNER" >
                                          <b>    ${game.secondPlayer.name}  </b>
                                     <#else>
                                             ${game.secondPlayer.name}
                                     </#if>
                                    </div>
                                </td>
                                <td class="kimborder">${game.gameResultAsString}</td>
                            </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </table>
</body>
</html>