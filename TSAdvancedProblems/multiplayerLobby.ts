function createLobby<T>(players: T[]): {
  totalPlayers: number
  players: T[]
} {
  return {
    totalPlayers: players.length,
    players
  }
}

console.log("players list",
  createLobby(["Player1", "Player2", "Player3"])
)
