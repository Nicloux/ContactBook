$(document).ready(function() {
    $('#JTableContainer').jtable({
    	selecting: true,
        multiselect: true,
        selectingCheckboxes: true, //Show checkboxes on first column
        title : 'Contacts',
        actions : {
            listAction : 'Controller/contact?action=list',
            createAction: 'Controller/contact?action=create',
        	updateAction: 'Controller/contact?action=update',
            deleteAction: 'Controller/contact?action=delete'
        },
        fields : {
            id : {
                title : 'Id',
                width : '2%',
                key: true,
                create: false,
                edit: false,
                list: true,
                sorting: false
            },
            //CHILD TABLE DEFINITION FOR "EXAMS"
            Addresses: {
                title: '',
                width: '2%',
                sorting: false,
                edit: false,
                create: false,
                display: function (contact) {
                    //Create an image that will be used to open child table
                    var $img = $('<img src="resources/css/metro/list_metro.png" title="Edit addresses" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#JTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                title: contact.record.firstName + ' ' + contact.record.lastName + ' - Addresses',
                                actions: {
                                    listAction: '/ContactBook/Controller/address?action=list&contactId=' + contact.record.id,
                                },
                                fields: {
                                    contactId: {
                                        type: 'hidden',
                                        defaultValue: contact.record.contactId
                                    },
                                    type: {
                                        title: 'Type',
                                        width: '10%'
                                    },
                                    street: {
                                        title: 'Street',
                                        width: '30%',
                                    },
                                    zip: {
                                        title: 'Zip',
                                        width: '10%',
                                    },
                                    city: {
                                        title: 'City',
                                        width: '10%',
                                    },
                                    country: {
                                        title: 'Country',
                                        width: '10%',
                                    },
                                }
                            }, function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            firstName : {
                title : 'First Name',
                width : '15%',
                edit : true
            },
            lastName : {
                title : 'Last Name',
                width : '15%',
                edit : true
            },
            birthDate : {
                title : 'Birthdate',
                width : '10%',
                edit : true,
                sorting: false
            },
            phoneNumber : {
                title : 'Phone Number',
                width : '10%',
                edit : true
            },
            email : {
                title : 'Email',
                width : '15%',
                edit : true
            },
            isActive : {
                title : 'isActive',
                width : '12%',
                type: 'checkbox',
                values: { 'false': 'Passive', 'true': 'Active' },
                edit : true
            },
        }
        /*//Register to selectionChanged event to hanlde events
        selectionChanged: function () {
            //Get all selected rows
            var $selectedRows = $('#JTableContainer').jtable('selectedRows');

            $('#SelectedRowList').empty();
            if ($selectedRows.length > 0) {
                //Show selected rows
                $selectedRows.each(function () {
                    var record = $(this).data('record');
                    $('#SelectedRowList').append(
                        '<b>ContactId</b>: ' + record.id +
                        '<br /><b>First Name</b>: ' + record.firstName + ' ' + '<b>First Name</b>:' + record.lastName + '<br /><br />'
                        );
                });
            } else {
                //No rows selected
                $('#SelectedRowList').append('No row selected.');
            }
        },
        rowInserted: function (event, data) {
            if (data.record.Name.indexOf('Andrew') >= 0) {
                $('#JTableContainer').jtable('selectRows', data.row);
            }
        }*/
    });
    
    $('#JTableContainer').jtable('load');
    
   /* //Delete selected contacts
    $('#DeleteAllButton').button().click(function () {
        var $selectedRows = $('#StudentTableContainer').jtable('selectedRows');
        $('#JTableContainer').jtable('deleteRows', $selectedRows);
    });*/
});