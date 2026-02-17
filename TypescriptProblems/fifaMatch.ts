interface Match {
  homeTeam: string
  awayTeam: string
  homeScore: number
  awayScore: number
  stadium: string
  matchDate: Date
  isFinished: boolean
}

const match1: Match = {
  homeTeam: "India",
  awayTeam: "Pakistan",
  homeScore: 2,
  awayScore: 1,
  stadium: "Dubai",
  matchDate: new Date("2026-02-10"),
  isFinished: true
}

const match2: Match = {
  homeTeam: "Australia",
  awayTeam: "Zimbabwe",
  homeScore: 3,
  awayScore: 3,
  stadium: "Olympici",
  matchDate: new Date("2026-02-11"),
  isFinished: true
}

const match3: Match = {
  homeTeam: "Spain",
  awayTeam: "Italy",
  homeScore: 0,
  awayScore: 0,
  stadium: "Regus",
  matchDate: new Date("2026-02-12"),
  isFinished: false
}

function getMatchResult(match: Match): string {
  if (!match.isFinished) {
    return "Match not completed"
  }

  if (match.homeScore > match.awayScore) {
    return match.homeTeam
  } else if (match.homeScore < match.awayScore) {
    return match.awayTeam
  } else {
    return "Draw"
  }
}

console.log(getMatchResult(match1))
console.log(getMatchResult(match2))
console.log(getMatchResult(match3))

