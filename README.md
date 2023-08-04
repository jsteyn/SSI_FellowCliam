# README.md

Few things frustrate me as much as institutions with their Word and Excel documents that you have to complete for claiming your expenses. I use Linux. I do not have Word and Excel and I do not want Word and Exel. I do not want to use Word and Excel. So when I'm forced to use these applications I start with my frustration level at 8 out of 10 - not a very good place to start.

I usually open the documents with LibreOffice which immediately elevates the frustration level to 9 out of 10 because the document's formatting goes to pot, formulae stop working etc. etc. etc.

So after a few days of struggling to get yet another expense form filled in I took an evening to write this program for completing my SSI Fellowship claims. Unfortunately, I made the unwise decision to also make it an exercise in avoiding the use of a singleton which I like to use for little applications like these that are driven by central JTable/TableModel. If anyone wants to convince me that the use of a singleton in this scenario is not advisable they'll have a lot of explaining to do because I believe not using a singleton made this code excessively complicated, difficult to follow and in regard to maintainability, a total mess.

Why organisations, in this day and age, still want to use Excel and Word docs for this kind of thing I would never understand ...