// lazyload config

angular.module('app')
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  .constant('JQ_CONFIG', {
      screenfull:     [   'bower_components/screenfull/dist/screenfull.min.js'],
      slimScroll:     [   'bower_components/slimscroll/jquery.slimscroll.min.js'],
      nestable:       [   'bower_components/nestable/jquery.nestable.js',
                          'bower_components/nestable/jquery.nestable.css'],
      filestyle:      [   'bower_components/bootstrap-filestyle/src/bootstrap-filestyle.js'],
      chosen:         [   'bower_components/chosen/chosen.jquery.min.js',
                          'bower_components/bootstrap-chosen/bootstrap-chosen.css'],
      footable:       [   'bower_components/footable/dist/footable.all.min.js',
                          'bower_components/footable/css/footable.core.css']

    }
  )
  // oclazyload config
  .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  true,
          events: true,
          modules: [
              {
                  name: 'ngGrid',
                  files: [
                      'bower_components/ng-grid/build/ng-grid.min.js',
                      'bower_components/ng-grid/ng-grid.min.css',
                      'bower_components/ng-grid/ng-grid.bootstrap.css'
                  ]
              },
              {
                  name: 'ui.grid',
                  files: [
                      'bower_components/angular-ui-grid/ui-grid.min.js',
                      'bower_components/angular-ui-grid/ui-grid.min.css',
                      'bower_components/angular-ui-grid/ui-grid.bootstrap.css'
                  ]
              },
              {
                  name: 'ui.select',
                  files: [
                      'bower_components/angular-ui-select/dist/select.min.js',
                      'bower_components/angular-ui-select/dist/select.min.css'
                  ]
              },
              {
                  name:'angularFileUpload',
                  files: [
                    'bower_components/angular-file-upload/angular-file-upload.min.js'
                  ]
              },
              {
                  name:'ui.calendar',
                  files: ['bower_components/angular-ui-calendar/src/calendar.js']
              },
              {
                  name: 'angularBootstrapNavTree',
                  files: [
                      'bower_components/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
                      'bower_components/angular-bootstrap-nav-tree/dist/abn_tree.css'
                  ]
              },
              {
                  name: 'textAngular',
                  files: [
                      'bower_components/textAngular/dist/textAngular-sanitize.min.js',
                      'bower_components/textAngular/dist/textAngular.min.js'
                  ]
              },
              {
                  name: 'smart-table',
                  files: [
                      'bower_components/angular-smart-table/dist/smart-table.min.js'
                  ]
              }
          ]
      });
  }])
;
