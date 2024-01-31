export interface Ticket {
    type: "SINGLE"|"TIMED"|"SEASON"
    fare: "FULL"|"HALF"
    id: string
    start?: Date
    end?: Date
    duration?: number
    timestamp?: Date
    vehicleId?: string
}
