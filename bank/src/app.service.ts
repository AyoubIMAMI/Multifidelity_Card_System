import { Injectable } from '@nestjs/common';

import { PaymentDto } from './dto/payment.dto';
import { PaymentRejectedException } from './exceptions/payment-rejected-exception';

@Injectable()
export class AppService {
  private static readonly magicKey: string = '896983'; // ASCII code for 'YES'

  private transactions: Array<PaymentDto>;

  constructor() {
    this.transactions = [];
  }

  findAll(): PaymentDto[] {
    return this.transactions;
  }

  refill(paymentDto: PaymentDto): PaymentDto {
    console.log(
      "Received request: {creditCard='" +
        paymentDto.creditCard +
        "', amount=" +
        paymentDto.amount +
        '}',
    );
    if (paymentDto.creditCard.includes(AppService.magicKey)) {
      this.transactions.push(paymentDto);
      console.log('OK for payment, sending back:' + paymentDto.amount);
      return paymentDto;
    } else {
      console.log('*NOK* for payment, sending back: 0');
      throw new PaymentRejectedException(paymentDto.amount);
    }
  }
}
