import { NextResponse } from 'next/server';

export async function GET() {
    const bills: BillRecord[] = [
        {
            id: '1',
            amount: '1000',
            type: 'INVOICE',
            note: 'Payment for office supplies',
            Status: 'APPROVED',
            createdAt: '2025-03-20T08:00:00Z',
            updatedAt: '2025-03-21T10:00:00Z',
        },
        {
            id: '2',
            amount: '500',
            type: ['RECEIPT', 'OTHER'],
            note: 'Taxi fee reimbursement',
            Status: 'PENDING',
            createdAt: '2025-03-18T09:30:00Z',
            updatedAt: '2025-03-19T12:45:00Z',
        },
    ];

    return NextResponse.json(bills);
}