<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HROne new Leads</title>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .contact-info {
            margin-bottom: 5px;
        }
        .contact-info label {
            font-weight: bold;
            display: inline-block;
            width: 100px;
        }
        .message {

        }
    </style>
</head>
<body style="font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;">
    <div class="contact-info">
        <label for="firstName">First Name:</label>
        <span id="firstName">${first_name!''}</span>
    </div>
    <div class="contact-info">
        <label for="lastName">Last Name:</label>
        <span id="lastName">${last_name!''}</span>
    </div>
    <div class="contact-info">
        <label for="telephone">Telephone:</label>
        <span id="telephone">${tel!''}</span>
    </div>
    <div class="contact-info">
        <label for="workEmail">Work Email:</label>
        <a href="mailto:${email!''}" id="workEmail">${email!''}</a>
    </div>
    <div class="contact-info">
        <label for="userMessage">Message:</label>
        <span id="userMessage" class="message">${msg!''}</span>
    </div>
    <div class="contact-info">
        <label for="userMessage">IP:</label>
        <span id="userMessage">${ipAddr!''}</span>
    </div>
</body>
</html>
