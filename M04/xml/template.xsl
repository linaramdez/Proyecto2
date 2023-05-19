<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/BATTLE">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
                <title>Battle</title>
                <link rel="stylesheet" type="text/css" href="index.css"/> 

            </head>
            <body>
                <main class="table">
                    <section class="table_header">
                        <h1>BATALLAS de RACES</h1>
                    </section>
                    <section class="table_body">
                        <table>
                            <thead>
                                <tr>
                                    <th>Battle</th>
                                    <th>Player</th>
                                    <th>Warrior</th>
                                    <th>Warrior weapon</th>
                                    <th>Opponent</th>
                                    <th>Opponent weapon</th>
                                    <th>Injuries Caused</th>
                                    <th>Injuries Suffered</th>
                                    <th>Battle Points</th>
                                </tr>
                            </thead>
                            <tbody>
                                <xsl:for-each select="ROW">
                                    <tr>
                                        <td><xsl:value-of select="battle_id"/></td>
                                        <td><img src="guerrero.jpg" alt=""><xsl:value-of select="player_id"/></img></td>
                                        <td><xsl:value-of select="warrior_id"/></td>
                                        <td><xsl:value-of select="warrior_weapon_id"/></td>
                                        <td><xsl:value-of select="opponent_id"/></td>
                                        <td><xsl:value-of select="opponent_weapon_id"/></td>
                                        <td><xsl:value-of select="injuries_caused"/></td>
                                        <td><xsl:value-of select="injuries_suffered"/></td>
                                        <td><xsl:value-of select="battle_points"/></td>
                                    </tr>
                                </xsl:for-each>
                            </tbody>
                        </table>
                    </section> 
                </main>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>