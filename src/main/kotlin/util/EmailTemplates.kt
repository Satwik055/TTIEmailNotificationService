package util

import model.CurrencyType
import model.EmailTemplate
import model.TransactionStatus

fun getTransactionUpdateEmailTemplate(transactionCode:String,currency: CurrencyType, time:String, amount:String, status: TransactionStatus, username:String): EmailTemplate {
    return EmailTemplate(
        templateName = "Transaction Update",
        subject = "Transaction Update",
        body = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333333;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 1px solid #eeeeee;
        }
        .logo {
            max-width: 150px;
            height: auto;
        }
        .content {
            padding: 20px 0;
        }
        .transaction-details {
            background-color: #f8f9fa;
            border-radius: 4px;
            padding: 15px;
            margin: 20px 0;
        }
        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #eeeeee;
        }
        .detail-row:last-child {
            border-bottom: none;
        }
        .detail-label {
            font-weight: bold;
            color: #555555;
        }
        .status-badge {
            display: inline-block;
            padding: 4px 8px;
            background-color: #f39c12;
            color: white;
            border-radius: 4px;
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            margin: 10px 0;
        }
        .footer {
            text-align: center;
            padding-top: 20px;
            border-top: 1px solid #eeeeee;
            font-size: 12px;
            color: #777777;
        }
        .small {
            font-size: 12px;
            color: #777777;
        }
    </style>
</head>
<body>
<div class="container">
   <div class="header">
       <h2>Transaction Updated</h2>
   </div>

    <div class="content">
    
        <p>Hi $username</p>
        <p>The status of your transaction has been updated to $status</p>

        <div class="transaction-details">
            <div class="detail-row">
                <span class="detail-label">Transaction Code:&nbsp;</span>
                <span>$transactionCode</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Transaction Time:&nbsp;</span>
                <span>$time</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Amount:&nbsp;</span>
                <span>$amount</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Currency:&nbsp;</span>
                <span>$currency</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Status:&nbsp;</span>
                <span>$status</span>
            </div>
        </div>

        <p>If you didn't initiate this transaction, please <a href="mailto:hello@transfertoinr.com">contact our support team</a> immediately.</p>
    </div>

    <div class="footer">
        <p>&copy; 2025 TransferToInr. All rights reserved.</p>
        <p>This is an automated message - please do not reply directly to this email.</p>
    </div>
</div>
</body>
</html>
        """.trimIndent()
    )
}

fun getAdminNotifyEmailTemplate(transactionCode:String,currency: CurrencyType, senderName:String, reason:String, accountNumber:String, recipientName:String, paymentScreenshotLink:String, ifscCode:String, time:String, amountSent:String, bank:String,  amountReceive:String, status: TransactionStatus, senderPhone:String, senderEmail:String): EmailTemplate {
    return EmailTemplate(
        templateName = "Admin Transaction Notification",
        subject = "New Transaction Initiated",
        body = """
            <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333333;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            border-bottom: 1px solid #eeeeee;
        }
        .logo {
            max-width: 150px;
            height: auto;
        }
        .transaction-details {
            background-color: #f8f9fa;
            border-radius: 4px;
            padding: 15px;
            margin: 20px 0;
        }
        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #eeeeee;
        }
        .detail-row:last-child {
            border-bottom: none;
        }
        .detail-label {
            font-weight: bold;
            color: #555555;
        }
        .status-badge {
            display: inline-block;
            padding: 4px 8px;
            background-color: #f39c12;
            color: white;
            border-radius: 4px;
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            margin: 10px 0;
        }
        .footer {
            text-align: center;
            padding-top: 20px;
            border-top: 1px solid #eeeeee;
            font-size: 12px;
            color: #777777;
        }
        .small {
            font-size: 12px;
            color: #777777;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="content">
        <p>Hey Team</p>
        <p>A new transaction has been initiated by $senderName, below are the details:</p>

        <div class="transaction-details">
            <div class="detail-row">
                <span class="detail-label">Transaction Code:&nbsp;</span>
                <span>$transactionCode</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Transaction Time:&nbsp;</span>
                <span>$time</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Currency:&nbsp;</span>
                <span>$currency</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Status:&nbsp;</span>
                <span>$status</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Sender's name:&nbsp;</span>
                <span>$senderName</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Sender's email:&nbsp;</span>
                <span>$senderEmail</span>
            </div>       
            <div class="detail-row">
                <span class="detail-label">Sender's phone:&nbsp;</span>
                <span>$senderPhone</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Amount Sent:&nbsp;</span>
                <span>$amountSent $currency</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Amount to receive:&nbsp;</span>
                <span>₹$amountReceive</span>
            </div> 
            <div class="detail-row">
                <span class="detail-label">Reason:&nbsp;</span>
                <span>$reason</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Payment screenshot link:&nbsp;</span>
                <span>$paymentScreenshotLink</span>
            </div> 
            <div class="detail-row">
                <span class="detail-label">Recipient's Name:&nbsp;</span>
                <span>$recipientName</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">IFSC CODE:&nbsp;</span>
                <span>$ifscCode</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Account Number:&nbsp;</span>
                <span>$accountNumber</span>
            </div>         
            <div class="detail-row">
                <span class="detail-label">Bank:&nbsp;</span>
                <span>$bank</span>
            </div>
        </div>

        <p>Kindly verify this into this transaction and process it.</p>

    </div>

    <div class="footer">
        <p>&copy; 2023 TransferToInr. All rights reserved.</p>
        <p>This is an automated message - please do not reply directly to this email.</p>
    </div>
</div>
</body>
</html>"""
    )
}

fun getNewTransactionEmailTemplate(transactionCode:String, time:String, amount:String, currency:CurrencyType, username:String, status:TransactionStatus): EmailTemplate {
    return EmailTemplate(
        templateName = "New Transaction",
        subject = "New Transaction",
        body = """
            <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333333;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 1px solid #eeeeee;
        }
        .logo {
            max-width: 150px;
            height: auto;
        }
        .content {
            padding: 20px 0;
        }
        .transaction-details {
            background-color: #f8f9fa;
            border-radius: 4px;
            padding: 15px;
            margin: 20px 0;
        }
        .detail-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #eeeeee;
        }
        .detail-row:last-child {
            border-bottom: none;
        }
        .detail-label {
            font-weight: bold;
            color: #555555;
        }
        .status-badge {
            display: inline-block;
            padding: 4px 8px;
            background-color: #f39c12;
            color: white;
            border-radius: 4px;
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            margin: 10px 0;
        }
        .footer {
            text-align: center;
            padding-top: 20px;
            border-top: 1px solid #eeeeee;
            font-size: 12px;
            color: #777777;
        }
        .small {
            font-size: 12px;
            color: #777777;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>New Transaction</h2>
    </div>

    <div class="content">
        <p>Hi $username</p>
        <p>We've successfully received your transaction request. Here are the details:</p>

        <div class="transaction-details">
            <div class="detail-row">
                <span class="detail-label">Transaction Code:&nbsp;</span>
                <span>$transactionCode</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Transaction Time:&nbsp;</span>
                <span>$time</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Amount:&nbsp;</span>
                <span>$amount</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Currency:&nbsp;</span>
                <span>$currency</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Status:&nbsp;</span>
                <span>$status</span>
            </div>
        </div>

        <p>The transaction is currently being processed. You'll receive another notification once it's completed.</p>

        <p>If you didn't initiate this transaction, please <a href="mailto:hello@transfertoinr.com">contact our support team</a> immediately.</p>
    </div>

    <div class="footer">
        <p>&copy; 2023 TransferToInr. All rights reserved.</p>
        <p>This is an automated message - please do not reply directly to this email.</p>
    </div>
</div>
</body>
</html>
        """.trimIndent()
    )
}

