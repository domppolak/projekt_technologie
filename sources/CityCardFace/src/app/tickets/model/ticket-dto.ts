export interface TicketDto {
    type: "SINGLE"|"TIMED"|"SEASON"
    fare: "FULL"|"HALF"
    id: string
    start?: string
    end?: string
    duration?: number
    timestamp?: Date
    vehicleId?: string
}
