
<classfile>
	<magic>justclassfile</magic>
	<minor_version>0</minor_version>
	<major_version>1</major_version>

    <constant_pool>
    	<constant_class index="1">
    		<name_index>2</name_index>
    	</constant_class>

		<constant_utf8 index="2">
			<bytes>ATest</bytes>
		</constant_utf8>

		<constant_utf8 index="3">
			<bytes>Code</bytes>
		</constant_utf8>

		<constant_utf8 index="4">
			<bytes>I</bytes>
		</constant_utf8>

		<constant_utf8 index="5">
			<bytes>global</bytes>
		</constant_utf8>

		<constant_utf8 index="6">
			<bytes>doIt</bytes>
		</constant_utf8>

		<constant_utf8 index="7">
			<bytes>(I)I</bytes>
		</constant_utf8>

		<constant_utf8 index="8">
			<bytes>main</bytes>
		</constant_utf8>

		<constant_utf8 index="9">
			<bytes>()V</bytes>
		</constant_utf8>

    	<constant_methodref index="10">
    		<class_index>1</class_index>
    		<name_and_type_index>11</name_and_type_index>
    	</constant_methodref>

    	<constant_name_and_type index="11">
    		<name_index>6</name_index>
			<descriptor_index>7</descriptor_index>
    	</constant_name_and_type>

    	<constant_methodref index="12">
    		<class_index>1</class_index>
    		<name_and_type_index>13</name_and_type_index>
    	</constant_methodref>

    	<constant_name_and_type index="13">
    		<name_index>8</name_index>
			<descriptor_index>9</descriptor_index>
    	</constant_name_and_type>

		<constant_integer index="14">
			<bytes>10</bytes>
		</constant_integer>

		<constant_fieldref index="15">
    		<class_index>1</class_index>
    		<name_and_type_index>16</name_and_type_index>
    	</constant_fieldref>

    	<constant_name_and_type index="16">
    		<name_index>5</name_index>
			<descriptor_index>4</descriptor_index>
    	</constant_name_and_type>

    </constant_pool>

    <this_class>1</this_class>

	<field_info>
		<field>
			<name_index>5</name_index>
			<descriptor_index>4</descriptor_index>
		</field>
	</field_info>

    <method_info>
    	<method> <!-- doIt -->
			<name_index>6</name_index>
			<descriptor_index>7</descriptor_index>
			<attribute_info>
				<attribute>
					<attribute_name_index>3</attribute_name_index>
					<info>
						<max_stack>1</max_stack>
						<max_locals>1</max_locals>
						<code>
							ILOAD 0
							IRETURN
						</code>
					</info>
				</attribute>
			</attribute_info>
    	</method>

    	<method> <!-- main -->
			<name_index>8</name_index>
			<descriptor_index>9</descriptor_index>
			<attribute_info>
				<attribute>
					<attribute_name_index>3</attribute_name_index>
					<info>
						<max_stack>1</max_stack>
						<max_locals>1</max_locals>
						<code>
							LDC_W 14
							INVOKESTATIC 10
							ISTORE 0
							ILOAD 0
							PUTSTATIC 15
							RETURN
						</code>
					</info>
				</attribute>
			</attribute_info>
    	</method>
    </method_info>
</classfile>
