interface Team {
  readonly id: number
  name: string
  players: string[]
  points: number
}

const teams: Team[] = [
  { id: 1, name: "Warriors", players: ["A", "B", "C"], points: 10 },
  { id: 2, name: "Titans", players: ["D", "E", "F"], points: 15 },
  { id: 3, name: "Rangers", players: ["G", "H", "I"], points: 20 }
]

function addPoints(team: Team, points: number): Team {
  return {
    ...team,
    points: team.points + points
  }
}

console.log(addPoints(teams[0], 5))
