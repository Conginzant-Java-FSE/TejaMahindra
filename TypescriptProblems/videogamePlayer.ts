const player1: {
  username: string
  level: number
  experiencePoints: number
  rank: "Bronze" | "Silver" | "Gold" | "Platinum"
} = {
  username: "ShadowHunter",
  level: 10,
  experiencePoints: 5000,
  rank: "Silver"
}

const player2: {
  username: string
  level: number
  experiencePoints: number
  rank: "Bronze" | "Silver" | "Gold" | "Platinum"
} = {
  username: "DragonSlayer",
  level: 20,
  experiencePoints: 15000,
  rank: "Gold"
}

function promotePlayer(player: {
  username: string
  level: number
  experiencePoints: number
  rank: "Bronze" | "Silver" | "Gold" | "Platinum"
}) {
  return {
    ...player,
    level: player.level + 1,
    experiencePoints: player.experiencePoints + 1000
  }
}
