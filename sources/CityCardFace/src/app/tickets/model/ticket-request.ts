export interface TicketRequest {
    passengerId: string
    type: "SINGLE"|"TIMED"|"SEASON"
    fare: "FULL"|"HALF"
    start?: Date
    end?: Date
    duration?: number
}
