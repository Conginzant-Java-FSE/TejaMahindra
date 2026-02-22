interface PlayerStats {
  name: string
  goals: number
  assists: number
  yellowCards: number
}

function getOffensiveStatss(
  stats: PlayerStats
): Pick<PlayerStats, "goals" | "assists"> {
  return {
    goals: stats.goals,
    assists: stats.assists
  }
}

console.log("goals & assists",
  getOffensiveStats({
    name: "Ronaldo",
    goals: 3,
    assists: 1,
    yellowCards: 2
  })
)
