# SaveInstanceInBundle
Save and load fields to/from a [Bundle]

# Usage

   class AppActivity : AppCompatActivity() {
  
    @SaveInstance
    private var mV: Object? = Object()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LoadSaveInstanceBundle.save(outState, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadSaveInstanceBundle.load(savedInstanceState, this)
    }

  }
  
  **Feel free to use

Note: This code was adapted from a library project named ![AndroidAutowire](https://github.com/CardinalNow/AndroidAutowire) which is licensed under the ![MIT license](https://raw.githubusercontent.com/CardinalNow/AndroidAutowire/master/LICENSE).
