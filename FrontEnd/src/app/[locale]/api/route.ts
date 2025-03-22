import { NextResponse } from "next/server";

export async function GET() {
    const bills: BillRecord[] = [
        {
            amount: '1000',
            type: 'INVOICE',
            note: 'Payment for office supplies',
            status: 'APPROVED',
            createdAt: '2025-03-20T08:00:00Z',
            updatedAt: '2025-03-21T10:00:00Z',
        },
        {
            amount: '500',
            type: ['RECEIPT', 'OTHER'],
            note: 'Taxi fee reimbursement',
            status: 'PENDING',
            createdAt: '2025-03-18T09:30:00Z',
            updatedAt: '2025-03-19T12:45:00Z',
        },
    ];

    return NextResponse.json(bills);
}
