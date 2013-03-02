<html>
<head>
    <title>Game List</title>
    <link rel="stylesheet" href="../style.css" type="text/css">
</head>
<body>
<table border="0" cellspacing="0" cellpadding="4">
    <tr valign="top">
        <td style="width: 24px"></td>
            <table border="0" cellspacing="0" cellpadding="4">
                <tr valign="top">
                    <td style="width: 24px"></td>
                    <td align="left" width="750"><h2>Match Details</h2>
                        <table border="0">
                            <tr>
                                <th align="center" width="50" class="kimborder">Game</th>
                                <th align="left" width="150" class="kimborder">Player A</th>
                                <th align="left" width="150" class="kimborder">Player B</th>
                            </tr>
                        <#list games as game>
                            <tr>
                                <td align="right" class="kimborder"><a href='game${game.gameNumber}.html?auto=true'>${game.gameNumber}</a> &nbsp;</td>
                                <td class="kimborder">
                                    <div>${game.firstPlayer.name}</div>
                                </td>
                                <td class="kimborder">
                                    <div>${game.secondPlayer.name}</div>
                                </td>
                            </tr>
                        </#list>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</body>
</html>