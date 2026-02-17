type Rank = "Bronze" | "Silver" | "Gold" | "Platinum"

type Player = {
  username: string
  level: number
  experiencePoints: number
  rank: Rank
}

const player1: Player = {
  username: "ShadowHunter",
  level: 10,
  experiencePoints: 5000,
  rank: "Silver"
}

const player2: Player = {
  username: "DragonSlayer",
  level: 20,
  experiencePoints: 15000,
  rank: "Gold"
}

function promotePlayer(player: Player): Player {
  return {
    ...player,
    level: player.level + 1,
    experiencePoints: player.experiencePoints + 1000
  }
}
