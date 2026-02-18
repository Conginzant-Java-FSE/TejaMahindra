interface Team {
  readonly id: number
  readonly name: string
  players: string[]
  rank: number
}

const team: Team = {
  id: 101,
  name: "CyberTitans",
  players: ["Ryu", "Blaze", "Nova"],
  rank: 5
}

function updateRank(team: Team, newRank: number): Team {
  return {
    ...team,
    rank: newRank
  }
}

console.log("updated:", updateRank(team, 2))
console.log("Original Team:", team)
